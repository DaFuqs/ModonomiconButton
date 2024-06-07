package net.modonomiconbutton.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "modonomiconbutton")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class ModonomiconButtonConfig implements ConfigData {

    @Comment("Modonomicon book identifier")
    public String bookIdentifier = "";
    public int posX = 127;
    public int posY = 61;
    @Comment("If false, open directly modpack book")
    public boolean openAllBooksScreen = true;
}
