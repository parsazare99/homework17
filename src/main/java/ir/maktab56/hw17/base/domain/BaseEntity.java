package ir.maktab56.hw17.base.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    private ID id;

    public void setId(ID id) {
        this.id = id;
    }


    public ID getId() {
        return id;
    }


}