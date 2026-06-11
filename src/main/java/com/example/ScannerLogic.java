package com.example;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import java.util.ArrayList;
import java.util.List;

public class ScannerLogic {

    public static void scanForBlock(Block targetBlock) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        ClientWorld world = client.world;
        BlockPos playerPos = client.player.getBlockPos();
        
        int chunkRadius = client.options.getClampedViewDistance();
        int playerChunkX = playerPos.getX() >> 4;
        int playerChunkZ = playerPos.getZ() >> 4;

        List<BlockPos> foundPositions = new ArrayList<>();
        
        // Block name will be retrieved based on client locale
        client.player.sendMessage(Text.literal("§7[Scanner] Searching for: §e").append(targetBlock.getName()), false);

        for (int cx = -chunkRadius; cx <= chunkRadius; cx++) {
            for (int cz = -chunkRadius; cz <= chunkRadius; cz++) {
                Chunk chunk = world.getChunk(playerChunkX + cx, playerChunkZ + cz, ChunkStatus.FULL, false);
                if (chunk == null) continue;

                for (BlockPos pos : chunk.getBlockEntityPositions()) {
                    if (world.getBlockState(pos).isOf(targetBlock)) {
                        foundPositions.add(pos);
                    }
                }
            }
        }

        if (foundPositions.isEmpty()) {
            client.player.sendMessage(Text.literal("§c[Scanner] No structures found in loaded chunks."), false);
        } else {
            client.player.sendMessage(Text.literal("§a[Scanner] Found objects: " + foundPositions.size()), false);
            for (BlockPos pos : foundPositions) {
                client.player.sendMessage(Text.literal("§6-> X: " + pos.getX() + " | Y: " + pos.getY() + " | Z: " + pos.getZ()), false);
            }
        }
    }
}
