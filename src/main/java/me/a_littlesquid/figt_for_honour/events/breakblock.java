package me.a_littlesquid.figt_for_honour.events;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import me.a_littlesquid.figt_for_honour.TownData;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class breakblock implements Listener {
    private Residence residence = Residence.getInstance();
    @EventHandler
    public void onBreakblock(BlockBreakEvent event){
        Block block=event.getBlock();
        Player player=event.getPlayer();
        String playername=player.getDisplayName();
        Location location=block.getLocation();
        TownData townData=new TownData();
        ClaimedResidence res = this.residence.getResidenceManager().getByLoc(location);
        if(res!=null) {
            String town=townData.gettownname(playername);
            if (townData.isFighting(town)){
                block.breakNaturally();
            }else {
                event.setCancelled(true);
            }
        }
    }
}
