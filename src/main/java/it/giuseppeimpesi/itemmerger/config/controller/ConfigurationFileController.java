package it.giuseppeimpesi.itemmerger.config.controller;

import it.giuseppeimpesi.itemmerger.config.ConfigurationFile;
import it.giuseppeimpesi.itemmerger.data.AbstractHandler;

public class ConfigurationFileController extends AbstractHandler<ConfigurationFile> {

    public void reloadAll() {
        getCache().forEach(((o, configurationFile) -> configurationFile.reload()));
    }

    public void saveAll() {
        getCache().forEach(((o, configurationFile) -> configurationFile.save()));
    }

    @Override
    public void add(Object object, ConfigurationFile element) {
        if (!(object instanceof String)) return;

        getCache().put(object, element);
    }
}