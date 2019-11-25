package it.unipr.fava_pellegrini;

import java.io.Serializable;

public class Workplace implements Serializable {
    private String name;
    private String address;

    public Workplace(final String name, final String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){
        return this.name + ", " + this.address;
    }
}
