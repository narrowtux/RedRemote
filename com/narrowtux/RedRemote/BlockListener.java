package com.narrowtux.RedRemote;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockListener extends org.bukkit.event.block.BlockListener {

	@Override
	public void onBlockRedstoneChange(BlockRedstoneEvent event){
		for(BlockFace face: BlockFace.values()){
			if(event.getBlock().getFace(face).getType().equals(Material.DIAMOND_BLOCK)){
				transmitSignal(event.getBlock().getFace(face), face, event.getNewCurrent()>0);
			}
		}
	}
	
	public void transmitSignal(Block origin, BlockFace direction, boolean value){
		System.out.println("Transmitting Signal "+direction+"!");
		Block current = origin;
		int iterations = 0;
		while(iterations<100){
			current = current.getFace(direction);
			if(current.getType().equals(Material.DIAMOND_BLOCK)){
				Block redstone = current.getFace(direction);
				if(redstone.getType().equals(Material.LEVER)){
					System.out.println("Found a Diamond block!");
					int data = (int)redstone.getData();
					if(value){
						if((data&0x08)!=0x08)
						{
							data|=0x08;
						}
					} else {
						if((data&0x08)==0x08){
							data^=0x08;
						}
					}
					redstone.setData((byte) data);
				}
			}
			iterations++;
		}
	}
}
