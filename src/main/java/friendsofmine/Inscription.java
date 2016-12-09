package friendsofmine;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Class representing an Inscription
 */
@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Utilisateur utilisateur;

    @NotNull
    @ManyToOne
    private Activite activite;

    @NotNull
    private Date dateInscription;

    public Long getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @PrePersist
    public void updateInscriptionDate() {
        setDateInscription(new Date());
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", activite=" + activite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inscription that = (Inscription) o;

        if (utilisateur != null ? !utilisateur.equals(that.utilisateur) : that.utilisateur != null) return false;
        if (activite != null ? !activite.equals(that.activite) : that.activite != null) return false;
        return dateInscription != null ? dateInscription.equals(that.dateInscription) : that.dateInscription == null;

    }

    @Override
    public int hashCode() {
        int result = utilisateur != null ? utilisateur.hashCode() : 0;
        result = 31 * result + (activite != null ? activite.hashCode() : 0);
        result = 31 * result + (dateInscription != null ? dateInscription.hashCode() : 0);
        return result;
    }
}
