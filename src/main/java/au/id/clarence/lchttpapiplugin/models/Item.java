package au.id.clarence.lchttpapiplugin.models;

import emu.lunarcore.game.enums.ItemRarity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
    private int id;
    private String itemName;
    private ItemRarity rarity;
}
