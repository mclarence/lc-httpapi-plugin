package au.id.clarence.lchttpapiplugin;

import au.id.clarence.lchttpapiplugin.endpoints.AvatarsEndpoint;
import au.id.clarence.lchttpapiplugin.endpoints.Endpoint;
import au.id.clarence.lchttpapiplugin.endpoints.ItemsEndpoint;
import emu.lunarcore.LunarCore;
import emu.lunarcore.plugin.Plugin;
import emu.lunarcore.util.JsonUtils;
import io.javalin.Javalin;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LcHttpApiPlugin extends Plugin {
    private final String logPrefix = "[HTTP-API] ";

    public LcHttpApiPlugin(Identifier identifier, URLClassLoader classLoader, File dataFolder, Logger logger) {
        super(identifier, classLoader, dataFolder, logger);
    }

    @Override
    public void onLoad() {
        String language = LunarCore.getConfig().getServerOptions().language;
        Map<Long, String> textMap = null;
        try {
            textMap = JsonUtils.loadToMap(LunarCore.getConfig().getResourceDir() + "/TextMap/TextMap" + language + ".json", Long.class, String.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var app = Javalin.create();

        Map<Long, String> finalTextMap = textMap;
        List<Endpoint> endpoints = new ArrayList<>(){
            {
                add(new AvatarsEndpoint(finalTextMap, app));
                add(new ItemsEndpoint(finalTextMap, app));
            }
        };

        // register the endpoints
        for (Endpoint endpoint: endpoints) {
            endpoint.registerEndpoint();
        }

        app.start(7070);
        getLogger().info(logPrefix + "Plugin loaded");
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
