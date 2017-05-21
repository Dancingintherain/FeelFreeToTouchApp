package de.htw_berlin.katharinapapke.feelfreetotouchapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (ArtistListItems) items.
     */
    public static final List<ArtistListItem> ITEMS = new ArrayList<ArtistListItem>();
    /**
     * A map of sample (ArtistListItem) items, by ID.
     */
    public static final Map<String, ArtistListItem> ITEM_MAP = new HashMap<String, ArtistListItem>();
    /**
     * An array of sample Artist Names.
     */
    public static final List<String> ARTIST_NAMES = new ArrayList<>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createArtistListItem(i));
        }
    }

    private static void addItem(ArtistListItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    // creates hard coded list of Artist Names - usually found in database or web
    private static void createListofArtists() {
        ARTIST_NAMES.add("Peter Posteurer - The Architecture Project");
        ARTIST_NAMES.add("Anna Kcornikow - Museum of Modern Art New York");
        ARTIST_NAMES.add("Malia Hi - Biennale 2009");
        ARTIST_NAMES.add("Chang Che Tsyvo - Biennale 2009");
        ARTIST_NAMES.add("Mirko Rausch - Biennale 2009");}

    private static ArtistListItem createArtistListItem(int position) {
        createListofArtists();
        return new ArtistListItem(String.valueOf(position), position + ". " + ARTIST_NAMES.get(position), getPicturePath());
    }

    public static String getPicturePath (){
        String filepath = "/Users/Berlina/AndroidStudioProjects/FeelFreeToTouchApp/app/src/main/res/drawable/artist_picture.jpeg";
        return filepath;
    }

    //For getting Pictures from the an URL

   /* public static String getBildURL (int position){
        String url = "http://lorempixel.com/600/400/cats/?fakeId="+ position;
        return url;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append(getBildURL(position));
        return builder.toString();
    }*/

    /**
     * An ArtistListitem representing a piece of content.
     */
    public static class ArtistListItem {
        public final String id;
        public final String content;
        public final String details;

        public ArtistListItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
