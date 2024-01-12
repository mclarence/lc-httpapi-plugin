package au.id.clarence.lchttpapiplugin.endpoints;

import io.javalin.Javalin;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public abstract class Endpoint {
    @NonNull protected final Map<Long, String> textMap;
    @NonNull protected final Javalin app;

    public void registerEndpoint() {}
}
