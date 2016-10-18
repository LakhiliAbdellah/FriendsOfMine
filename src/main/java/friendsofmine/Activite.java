package friendsofmine;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class corresponding to an activity
 */
public class Activite {


    @NotNull
    @Size(min = 1, max = 256)
    private String titre ;

    private String descriptif ;


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
}
