package it.unipr.fava_pellegrini;

import java.io.Serializable;

/**
 * Workplace Class
 * Each workplace has a string as an attribute containing its name, and another string
 * containing its address.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Workplace implements Serializable {
    private String name;
    private String address;

    /**
     * This constructor generates a Workplace from its parameters.
     *
     * @param name the workplace's name
     * @param address the workplace's address
     */
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

    /**
     * Return a string showing the workplace's attributes
     *
     * @return String the string
     *
     */
    @Override
    public String toString(){
        return this.name + ", " + this.address;
    }
}
