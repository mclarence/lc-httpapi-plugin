package au.id.clarence.lchttpapiplugin.endpoints;

import au.id.clarence.lchttpapiplugin.models.Item;
import emu.lunarcore.data.GameData;
import emu.lunarcore.data.excel.ItemExcel;
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


public class ItemsEndpoint extends Endpoint {
    public ItemsEndpoint(@NonNull Map<Long, String> textMap, @NonNull Javalin app) {
        super(textMap, app);
    }

    private final Handler fetchAllItems = ctx -> {
        List<Item> items = new ArrayList<>();
        var list = GameData.getItemExcelMap().keySet().intStream().sorted().boxed().toList();
        for (int id : list) {
            ItemExcel itemExcel = GameData.getItemExcelMap().get(id);
            items.add(new Item(itemExcel.getId(), textMap.getOrDefault(itemExcel.getItemName(), "null"), itemExcel.getRarity()));
        }

        ctx.contentType(ContentType.APPLICATION_JSON);
        ctx.result(JsonUtils.encode(items));
    };

    @Override
    public void registerEndpoint() {
        app.routes(() -> {
            path("items", () -> {
                get(fetchAllItems);
            });
        });
    }
}
