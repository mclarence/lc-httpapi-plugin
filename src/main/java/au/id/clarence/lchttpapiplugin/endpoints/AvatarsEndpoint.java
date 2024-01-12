package au.id.clarence.lchttpapiplugin.endpoints;

import au.id.clarence.lchttpapiplugin.models.Avatar;
import emu.lunarcore.data.GameData;
import emu.lunarcore.data.excel.AvatarExcel;
import emu.lunarcore.util.JsonUtils;
import io.javalin.Javalin;
import io.javalin.http.ContentType;
import io.javalin.http.Handler;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class AvatarsEndpoint extends Endpoint {

    private final Handler fetchAllAvatars = ctx -> {
        List<Avatar> avatars = new ArrayList<>();
        var list = GameData.getAvatarExcelMap().keySet().intStream().sorted().boxed().toList();
        for (int id : list) {
            AvatarExcel avatarExcel = GameData.getAvatarExcelMap().get(id);
            avatars.add(new Avatar(avatarExcel.getAvatarID(), textMap.getOrDefault(avatarExcel.getAvatarName(), "null"), avatarExcel.getDamageType()));
        }

        ctx.contentType(ContentType.APPLICATION_JSON);
        ctx.result(JsonUtils.encode(avatars));
    };

    public AvatarsEndpoint(@NonNull Map<Long, String> textMap, @NonNull Javalin app) {
        super(textMap, app);
    }

    @Override
    public void registerEndpoint() {
        app.routes(() -> {
            path("avatars", () -> {
                get(fetchAllAvatars);
            });
        });
    }
}
