package br.pucminas.controledetemperatura;

import java.util.List;

/**
 * Created by Felipe on 06/10/2017.
 */

public class ListaFeeds {

    public List<Feeds> feeds;

    public Channel channel;

    public List<Feeds> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feeds> feeds) {
        this.feeds = feeds;
    }
}
