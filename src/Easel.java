import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class Easel extends StackPane {
    private Artwork artwork;

    public Easel(Artwork artwork) {
        this.artwork = artwork;
        this.getChildren().add( artwork );
    }

    public Easel() {
        Artwork newArtwork = new Artwork();
        this.getChildren().add( newArtwork );
        this.artwork = newArtwork;
    }

    public Easel(Artwork artwork, Node... children) {
        super(children);
        this.artwork = artwork;
    }

    public void setArtwork( Artwork newArtwork ){
        this.getChildren().remove(artwork);
        this.getChildren().add( newArtwork );
        this.artwork = newArtwork;
    }

    public void resetArtwork(){
        this.getChildren().remove(artwork);
        Artwork newArtwork = new Artwork();
        this.getChildren().add( newArtwork );
        this.artwork = newArtwork;
    }

    public Artwork getArtwork() {
        return artwork;
    }

}
