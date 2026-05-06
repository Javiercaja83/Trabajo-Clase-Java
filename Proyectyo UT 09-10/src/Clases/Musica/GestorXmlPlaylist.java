package Clases.Musica;

import java.io.File;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;


public class GestorXmlPlaylist{

    public static void guardarPlaylist(List<Playlist> playlists) {
    try{

    DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element root = doc.createElement("playlists");
    doc.appendChild(root);

    for (Playlist p: playlists){
        if (p.getCanciones().isEmpty()) continue;

        Element playlist = doc.createElement("playlist");
        playlist.setAttribute("titulo", p.getTitulo());

        for (Cancion c: p.getCanciones()){

        
            Element cancion = doc.createElement("cancion");
            cancion.setAttribute("codigo", String.valueOf(c.getCodigo()));
            playlist.appendChild(cancion);
        }
        root.appendChild(playlist);
    }

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    transformer.transform(
        new DOMSource(doc),
        new StreamResult(new File("playlist.xml"))
    );

     System.out.println("Playlists guardadas en el XML");
   
    } catch (Exception e){
        e.printStackTrace();
    }


}


}
