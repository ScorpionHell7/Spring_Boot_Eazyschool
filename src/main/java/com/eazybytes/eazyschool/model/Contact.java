package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="contact_msg")
@NamedQueries(
        {
                @NamedQuery(name = "Contact.findOpenMsgs",
                query = "SELECT c FROM Contact c WHERE c.status = :status"),
                @NamedQuery(name = "Contact.updateMsgStatus",
                        query = "Update Contact c set c.status = ?1 where c.contactId = ?2")
        }
)
public class Contact extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy="native")
    @Column(name="contact_id")
    private int contactId;
    @NotBlank
    @Size(min = 3, message = "name should be atleast 3 characters long")
    private String name;
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digit")
    private String mobileNum;
    @NotBlank
    @Email(message = "Please provide valid email address")
    private  String email;
    @NotBlank
    @Size(min = 5, message = "subject should be atleast 5 characters long")
    private String subject;
    @NotBlank
    @Size(min = 7, message = "message should be atleast  7characters long")
    private String message;
    private String status;
}
