package erogenousbeef.bigreactors.common.multiblock.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erogenousbeef.bigreactors.common.BRLoader;
import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.multiblock.tileentity.TileEntityReactorControlRod;

public class BlockReactorControlRod extends BlockContainer {

	public BlockReactorControlRod(Material material) {
		super(material);
		
		this.setHardness(2.0f);
		this.setBlockName("blockReactorControlRod");
		this.setBlockTextureName(BigReactors.TEXTURE_NAME_PREFIX + "blockReactorControlRod");
		this.setCreativeTab(BigReactors.TAB);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityReactorControlRod();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		blockIcon =  par1IconRegister.registerIcon(BigReactors.TEXTURE_NAME_PREFIX + getUnlocalizedName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if(side == 1) { return blockIcon; }
		
		return BigReactors.blockReactorPart.getIcon(side, BlockReactorPart.METADATA_CASING);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
		if(entityPlayer.isSneaking()) {
			return false;
		}
		
		// Open GUI for this block
		if(!world.isRemote) {
			entityPlayer.openGui(BRLoader.instance, 0, world, x, y, z);
		}
		return true;
	}
	
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
		return false;
    }
}
