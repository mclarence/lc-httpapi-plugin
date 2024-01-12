package au.id.clarence.lchttpapiplugin.models;

import emu.lunarcore.game.enums.DamageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Avatar {
    private int id;
    private String avatarName;
    private DamageType element;
}
