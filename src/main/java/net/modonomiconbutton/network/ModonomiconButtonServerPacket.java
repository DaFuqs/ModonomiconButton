package net.modonomiconbutton.network;

import com.klikli_dev.modonomicon.book.*;
import com.klikli_dev.modonomicon.data.*;
import io.netty.buffer.*;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.registry.*;
import net.minecraft.server.network.*;
import net.minecraft.util.*;
import net.modonomiconbutton.*;

public class ModonomiconButtonServerPacket {
	
	public static final Identifier SEND_MODONOMICON_SCREEN_PACKET = new Identifier("modonomiconbutton", "send_modonomicon_screen_packet");
	public static final Identifier OPEN_MODPACK_SCREEN_PACKET = new Identifier("modonomiconbutton", "open_modpack_screen_packet");
	
	public static void init() {
		ServerPlayNetworking.registerGlobalReceiver(SEND_MODONOMICON_SCREEN_PACKET, (server, player, handler, buffer, sender) -> {
			server.execute(() -> {
				writeS2CModpackScreenPacket(player);
			});
		});
	}
	
	public static void writeS2CModpackScreenPacket(ServerPlayerEntity serverPlayerEntity) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		for (Book book : BookDataManager.get().getBooks().values()) {
			buf.writeIdentifier(book.getId());
			Item item = Registries.ITEM.get(book.getModel());
			if (item == Items.AIR) {
				item = Items.BOOK;
			}
			buf.writeIdentifier(Registries.ITEM.getId(item));
			buf.writeString(book.getName());
		}
		CustomPayloadS2CPacket packet = new CustomPayloadS2CPacket(OPEN_MODPACK_SCREEN_PACKET, buf);
		serverPlayerEntity.networkHandler.sendPacket(packet);
	}
}
