import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
/**
 * @author Julia Filzinger
 * An Easel holds an Artwork.
 */

public class Easel extends  StackPane {
    private Artwork artwork;

    Easel() {
        Artwork newArtwork = new Artwork();
        this.getChildren().add( newArtwork );
        this.artwork = newArtwork;
    }

    public void setArtwork( Artwork newArtwork ){
        this.getChildren().remove(artwork);
        this.getChildren().add( newArtwork );
        this.artwork = newArtwork;
    }

    void resetArtwork(){
        // replaces the current artwork by the default one
        this.getChildren().remove(artwork);
        Artwork newArtwork = new Artwork();
        this.getChildren().add( newArtwork );
        this.artwork = newArtwork;
    }

    void resetArtwork( Artwork oldArtwork ){
        // replaces the current artwork by an empty one with the same specs
        this.getChildren().remove(artwork);
        Artwork newArtwork = new Artwork( oldArtwork.getWidth(), oldArtwork.getHeight(), oldArtwork.getBgColor() );
        this.getChildren().add( newArtwork );
        this.artwork = newArtwork;
    }

    public Artwork getArtwork() {
        return artwork;
    }

}
