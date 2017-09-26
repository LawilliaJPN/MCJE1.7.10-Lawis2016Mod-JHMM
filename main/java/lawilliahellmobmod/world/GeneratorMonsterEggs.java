package lawilliahellmobmod.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import lawilliahellmobmod.api.HMMBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class GeneratorMonsterEggs implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int dim = world.provider.dimensionId;

		if (dim == -1 && world.provider instanceof WorldProviderSurface) {
			generateNetherOre(world, random, chunkX << 4, chunkZ << 4);
		}
	}

	private void generateNetherOre(World world, Random random, int x, int z) {
		//モンスターエッグ
		//黒オオカミ
		for(int i = 0; i < 30; i++) {
			int genX = x + random.nextInt(16);
			int genY = 10 + random.nextInt(90);
			int genZ = z + random.nextInt(16);
			new WorldGenMinable(HMMBlocks.meBlackWolf, 0, 4, Blocks.netherrack).generate(world, random, genX, genY, genZ);
		}

		//ヘルクリーパー
		for(int i = 0; i < 30; i++) {
			int genX = x + random.nextInt(16);
			int genY = 10 + random.nextInt(90);
			int genZ = z + random.nextInt(16);
			new WorldGenMinable(HMMBlocks.lavaBlockN, 0, 4, Blocks.netherrack).generate(world, random, genX, genY, genZ);
		}

		//特殊ネザーラック
		//コウモリブロック
		for(int i = 0; i < 30; i++) {
			int genX = x + random.nextInt(16);
			int genY = 30 + random.nextInt(70);
			int genZ = z + random.nextInt(16);
			new WorldGenMinable(HMMBlocks.batBlockN, 0, 4, Blocks.netherrack).generate(world, random, genX, genY, genZ);
		}

		//有毒ブロック
		for(int i = 0; i < 40; i++) {
			int genX = x + random.nextInt(16);
			int genY = 10 + random.nextInt(90);
			int genZ = z + random.nextInt(16);
			new WorldGenMinable(HMMBlocks.bpBlockN, 0, 4, Blocks.netherrack).generate(world, random, genX, genY, genZ);
		}

		//TNTブロック
		for(int i = 0; i < 40; i++) {
			int genX = x + random.nextInt(16);
			int genY = 10 + random.nextInt(90);
			int genZ = z + random.nextInt(16);
			new WorldGenMinable(HMMBlocks.tntBlockN, 0, 4, Blocks.netherrack).generate(world, random, genX, genY, genZ);
		}

		//溶岩ブロック
		for(int i = 0; i < 20; i++) {
			int genX = x + random.nextInt(16);
			int genY = 10 + random.nextInt(90);
			int genZ = z + random.nextInt(16);
			new WorldGenMinable(HMMBlocks.lavaBlockN, 0, 4, Blocks.netherrack).generate(world, random, genX, genY, genZ);
		}
	}
}
