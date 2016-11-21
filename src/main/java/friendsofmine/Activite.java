package friendsofmine;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class corresponding to an activity
 */
@Entity
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 256)
    private String titre ;

    private String descriptif ;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Utilisateur responsable ;


    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Utilisateur getResponsable() {
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activite activite = (Activite) o;

        if (!titre.equals(activite.titre)) return false;
        if (descriptif != null ? !descriptif.equals(activite.descriptif) : activite.descriptif != null) return false;
        return responsable.equals(activite.responsable);

    }

    @Override
    public int hashCode() {
        int result = titre.hashCode();
        result = 31 * result + (descriptif != null ? descriptif.hashCode() : 0);
        result = 31 * result + responsable.hashCode();
        return result;
    }
}
