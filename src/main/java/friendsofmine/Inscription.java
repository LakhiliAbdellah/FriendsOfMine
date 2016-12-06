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

        if (!utilisateur.equals(that.utilisateur)) return false;
        if (!activite.equals(that.activite)) return false;
        return dateInscription.equals(that.dateInscription);

    }

    @Override
    public int hashCode() {
        int result = utilisateur.hashCode();
        result = 31 * result + activite.hashCode();
        result = 31 * result + dateInscription.hashCode();
        return result;
    }
}
