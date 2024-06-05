package kleiders.jurisretro.interfaces;

import com.mojang.nbt.CompoundTag;

public interface EntityExtensions {
		default CompoundTag getExtraCustomData() {
			throw new RuntimeException("this should be overridden via mixin. what?");
		}

		default void syncExtraCustomData() {
			throw new RuntimeException("this should be overridden via mixin. what?");
		}

		default void setExtraCustomData(CompoundTag tag) {
			throw new RuntimeException("this should be overridden via mixin. what?");
		}
}
