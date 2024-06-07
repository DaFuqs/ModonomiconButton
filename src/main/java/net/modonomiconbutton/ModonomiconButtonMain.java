package net.modonomiconbutton;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.modonomiconbutton.config.ModonomiconButtonConfig;
import net.modonomiconbutton.network.ModonomiconButtonServerPacket;

public class ModonomiconButtonMain implements ModInitializer {

    public static final Identifier MODONOMICON_BUTTON = new Identifier("modonomiconbutton", "textures/gui/modonomicon_button.png");

    public static ModonomiconButtonConfig CONFIG = new ModonomiconButtonConfig();
    
    @Override
    public void onInitialize() {
        AutoConfig.register(ModonomiconButtonConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModonomiconButtonConfig.class).getConfig();
        ModonomiconButtonServerPacket.init();
    }

}
