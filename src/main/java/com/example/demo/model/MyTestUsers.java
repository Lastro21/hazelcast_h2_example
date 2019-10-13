package com.example.demo.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity
public class MyTestUsers implements Serializable {

    private static final long serialVersionUID = -8049349381142111441L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public MyTestUsers() {
    }

    public MyTestUsers(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTestUsers that = (MyTestUsers) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, address);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("address", address)
                .toString();
    }
}
