package ftn.isa.pharmacy.service.impl;


import ftn.isa.pharmacy.config.MailConfig;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.DermatologistRepository;
import ftn.isa.pharmacy.repository.PatientRepository;
import ftn.isa.pharmacy.repository.PharmacistRepository;
import ftn.isa.pharmacy.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MailServiceImpl implements MailService {

    final private MailConfig mailConfig;
    private final PharmacistRepository pharmacistRepository;
    private final DermatologistRepository dermatologistRepository;
    
    @Autowired
    public MailServiceImpl(MailConfig mailConfig, PharmacistRepository pharmacistRepository, DermatologistRepository dermatologistRepository) {
        this.mailConfig = mailConfig;
        this.pharmacistRepository = pharmacistRepository;
        this.dermatologistRepository = dermatologistRepository;
    }

    private JavaMailSenderImpl getJMS(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailConfig.getHost());
        mailSender.setPort(this.mailConfig.getPort());
        mailSender.setUsername(this.mailConfig.getUsername());
        mailSender.setPassword(this.mailConfig.getPassword());
        return mailSender;
    }

    private List<String> getUserInfo(Long id, String type){
        if(type.equals("ROLE_PHARMACIST")){
            Optional<Pharmacist> pharmacistOptional = pharmacistRepository.findById(id);
            if(pharmacistOptional.isPresent()){
                Pharmacist pharmacist = pharmacistOptional.get();
                List<String> info = new ArrayList<>();
                info.add(pharmacist.getEmail());
                info.add(pharmacist.getFirstName());
                return info;
            }
            else {
                throw new ResourceConflictException(1l,"Ne postoji farmaceut");
            }
        }else{
            Optional<Dermatologist> dermatologistOptional = dermatologistRepository.findById(id);
            if(dermatologistOptional.isPresent()){
                Dermatologist dermatologist = dermatologistOptional.get();
                List<String> info = new ArrayList<>();
                info.add(dermatologist.getEmail());
                info.add(dermatologist.getFirstName());
                return info;
            }
            else {
                throw new ResourceConflictException(1l,"Ne postoje dermatolog");
            }
        }
    }

    public void absenceAcceptedNotification(AbsenceRequest absenceRequest){
        JavaMailSenderImpl mailSender = getJMS();
        List<String> info = getUserInfo(absenceRequest.getEmployeeId(),absenceRequest.getTypeOfEmployee());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("apoteka@gmail.com");
        mailMessage.setTo(info.get(0));
        mailMessage.setSubject(absenceRequest.getTypeOfAbsence()+" - "+absenceRequest.getStatus());
        mailMessage.setText("Postovani " + info.get(1) + ",\n"+ "\n"+
                "Vas zahtev za " + absenceRequest.getTypeOfAbsence().toLowerCase() +
                " je prihvacen." +  "\n"+ "\n"+
                "Pozdrav," + "\n"+
                "AP tim");
        mailSender.send(mailMessage);
    }

    public void absenceDeclinedNotification(AbsenceRequest absenceRequest){
        JavaMailSenderImpl mailSender = getJMS();
        List<String> info = getUserInfo(absenceRequest.getEmployeeId(),absenceRequest.getTypeOfEmployee());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("apoteka@gmail.com");
        mailMessage.setTo(info.get(0));
        mailMessage.setSubject(absenceRequest.getTypeOfAbsence()+" - "+absenceRequest.getStatus());
        mailMessage.setText("Postovani " + info.get(1) + ",\n"+ "\n"+
                "Vas zahtev za " + absenceRequest.getTypeOfAbsence().toLowerCase() +
                " je odbijen." +  "\n"+ "\n"+
                "Razlog odbijanja: " + absenceRequest.getAnswerText() + "\n"+ "\n"+
                "Pozdrav," + "\n"+
                "AP tim");
        mailSender.send(mailMessage);
    }

    public void newPromotionNotification(Promotion promotion,Patient patient){
        JavaMailSenderImpl mailSender = getJMS();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("apoteka@gmail.com");
        mailMessage.setTo(patient.getEmail());
        mailMessage.setSubject("Nova " + promotion.getType() + " u " + promotion.getPharmacy().getName());
        mailMessage.setText("Postovani u " + promotion.getPharmacy().getName() +
                " ima nova " + promotion.getType() + " - " + promotion.getTitle() + ". Vise informacija na nasem sajtu!\n"+ "\n"+
                "Pozdrav," + "\n"+
                "AP tim");
        mailSender.send(mailMessage);
    }

    public void newSubscriptionForPromotion(Pharmacy pharmacy, Patient patient){
        JavaMailSenderImpl mailSender = getJMS();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("apoteka@gmail.com");
        mailMessage.setTo(patient.getEmail());
        mailMessage.setSubject("Akcije i promocije - " + pharmacy.getName() + "!");
        mailMessage.setText("Postovani,\n"+ "\n"+
                "Uspesno ste se pretplatili za dobijanje akcija i promocija - " +
                pharmacy.getName() +  ".\n"+ "\n"+
                "Pozdrav," + "\n"+
                "AP tim");
        mailSender.send(mailMessage);
    }

    public void purchaseOrderNotification(String suppEmail, String subj, String text){
        JavaMailSenderImpl mailSender = getJMS();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("apoteka@gmail.com");
        mailMessage.setTo(suppEmail);
        mailMessage.setSubject(subj);
        mailMessage.setText("Postovani,\n"+ "\n"+
                text + "\n"+
                "Pozdrav," + "\n"+
                "AP tim");
        mailSender.send(mailMessage);
    }
}
