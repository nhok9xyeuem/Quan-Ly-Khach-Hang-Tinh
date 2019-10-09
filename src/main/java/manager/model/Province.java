package manager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameProvince;
    private int codeProvince;
    @OneToMany(mappedBy = "province")
    private Set<Customer> customers;

    public Province() {
    }

    public Province(String nameProvince, int codeProvince, Set<Customer> customers) {
        this.nameProvince = nameProvince;
        this.codeProvince = codeProvince;
        this.customers = customers;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public int getCodeProvince() {
        return codeProvince;
    }

    public void setCodeProvince(int codeProvince) {
        this.codeProvince = codeProvince;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
