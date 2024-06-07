package net.modonomiconbutton.mixin.client;

import com.klikli_dev.modonomicon.api.*;
import com.klikli_dev.modonomicon.client.gui.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.modonomiconbutton.ModonomiconButtonMain;
import net.modonomiconbutton.network.ModonomiconButtonClientPacket;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> {

    public InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void mouseClickedMixin(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> info) {
        if (this.client != null && this.focusedSlot == null && this.isPointWithinBounds(ModonomiconButtonMain.CONFIG.posX, ModonomiconButtonMain.CONFIG.posY, 20, 18, (double) mouseX, (double) mouseY))
            if (ModonomiconButtonMain.CONFIG.openAllBooksScreen) {
                ModonomiconButtonClientPacket.writeC2SModpackScreenPacket(client);
            } else {
                BookGuiManager.get().openBook(new Identifier(ModonomiconButtonMain.CONFIG.bookIdentifier));
            }
    }

    @Inject(method = "drawBackground", at = @At("TAIL"))
    protected void drawBackgroundMixin(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo info) {
        if (this.isPointWithinBounds(ModonomiconButtonMain.CONFIG.posX, ModonomiconButtonMain.CONFIG.posY, 20, 18, mouseX, mouseY)) {
            context.drawTexture(ModonomiconButtonMain.MODONOMICON_BUTTON, this.x + ModonomiconButtonMain.CONFIG.posX, this.y + ModonomiconButtonMain.CONFIG.posY, 166, 0, 20, 18);
            context.drawTooltip(this.textRenderer, Text.translatable("screen.modonomiconbutton"), mouseX, mouseY);
        } else {
            context.drawTexture(ModonomiconButtonMain.MODONOMICON_BUTTON, this.x + ModonomiconButtonMain.CONFIG.posX, this.y + ModonomiconButtonMain.CONFIG.posY, 146, 0, 20, 18);
        }
    }
}
