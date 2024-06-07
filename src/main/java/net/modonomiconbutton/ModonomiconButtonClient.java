package net.modonomiconbutton;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.modonomiconbutton.network.ModonomiconButtonClientPacket;

@Environment(EnvType.CLIENT)
public class ModonomiconButtonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModonomiconButtonClientPacket.init();
    }

}
