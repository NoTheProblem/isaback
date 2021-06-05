package ftn.isa.pharmacy.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_SYSADMIN")
public class SysAdmin extends User {


}
