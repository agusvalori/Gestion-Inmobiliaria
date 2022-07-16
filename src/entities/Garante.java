package entities;

/**
 *
 * @author agusv
 */

public class Garante {
    private Integer id;
    private Persona persona;

    public Garante() {
    }

    public Garante(Integer id, Persona persona) {
        this.id = id;
        this.persona = persona;

    }

    public Integer getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
