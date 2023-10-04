package pl.stb2trello.secrets;

public class TrelloSecrets {

    private TrelloSecrets(){}

    private static final String KEY = "3dce6f67a1fc2ee98c14c98e1e310569";
    private static final String TOKEN = "773524badb9b285294a43350a4b640169c0f47cc729248cfd9251887527fd33e";

    public static String getKey(){
        return KEY;
    }

    public static String getToken(){
        return TOKEN;
    }
}
