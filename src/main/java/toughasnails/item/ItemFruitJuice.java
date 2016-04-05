/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package toughasnails.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toughasnails.api.item.ItemDrink;
import toughasnails.api.thirst.IDrink;
import toughasnails.item.ItemFruitJuice.JuiceType;

public class ItemFruitJuice extends ItemDrink<JuiceType>
{
    @Override
    public JuiceType getTypeFromMeta(int meta) 
    {
        return JuiceType.values()[meta % JuiceType.values().length];
    }
    
    // get the correct name for this item by looking up the meta value in the DartType enum
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item.juice_" + getTypeFromMeta(stack.getMetadata()).toString();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subItems)
    {
        for (JuiceType juiceType : JuiceType.values())
        {
            subItems.add(new ItemStack(item, 1, juiceType.ordinal()));
        }
    }
    
    public static enum JuiceType implements IDrink, IStringSerializable
    {
        APPLE(5, 0.6F), 
        BEETROOT(9, 0.6F), 
        CACTUS(7, 0.1F), 
        CARROT(4, 0.5F), 
        GOLDEN_APPLE(15, 1.0F), 
        GOLDEN_CARROT(13, 0.8F), 
        MELON(5, 0.3F), 
        PUMPKIN(3, 0.2F);
        
        private int thirst;
        private float hydration;
        
        private JuiceType(int thirst, float hydration)
        {
            this.thirst = thirst;
            this.hydration = hydration;
        }
        
        @Override
        public int getThirst()
        {
            return this.thirst;
        }
        
        @Override
        public float getHydration()
        {
            return this.hydration;
        }
        
        @Override
        public float getPoisonChance() 
        {
            return 0.0F;
        }
        
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        
        @Override
        public String toString()
        {
            return this.getName();
        }
    }
}
