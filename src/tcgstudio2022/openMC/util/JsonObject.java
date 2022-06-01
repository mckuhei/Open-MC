package tcgstudio2022.openMC.util;

import com.google.gson.*;
import tcgstudio2022.openMC.resources.model.BlockModel;

import java.lang.reflect.Type;

public interface JsonObject<T> extends JsonDeserializer<T>,JsonSerializer<T> {
}
