package ra.module5.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name",unique = true,nullable = false,columnDefinition = "varchar(100)")
    private String userName;
    @Column(name = "email",nullable = false,unique = true,columnDefinition = "varchar(255)")
    private String email;
    @Column(name="full_name",columnDefinition = "varchar(100)",nullable = false)
    private String fullName;
    @Column(name = "user_status")
    private boolean userStatus;
    @Column(name = "password",nullable = false,columnDefinition = "varchar(255)")
    private String password;
    @Column(name = "avatar",columnDefinition = "varchar(255)")
    private String avatar;
    @Column(name = "phone",columnDefinition = "varchar(15)")
    private String phone;
    @Column(name = "address",columnDefinition = "varchar(255)")
    private String address;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "created_at")
    private String created;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "update_at")
    private String updated;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Roles",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id"))
    private Set<Roles> listRoles = new HashSet<>();
}

