package ftn.isa.pharmacy.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ROLE_SYSADMIN")
public class SysAdmin extends User {


}
