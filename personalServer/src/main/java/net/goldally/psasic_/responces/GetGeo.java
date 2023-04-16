package net.goldally.psasic_.responces;

import com.google.gson.JsonElement;
import net.goldally.psasic_.misc.GEO;

public class GetGeo extends Minimal {
    private final double latitude;
    private final double longitude;

    public GetGeo(double latitude, double longitude) {
        super(200, "OK");
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
