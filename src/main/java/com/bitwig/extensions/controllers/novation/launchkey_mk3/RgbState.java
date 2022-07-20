package com.bitwig.extensions.controllers.novation.launchkey_mk3;

import com.bitwig.extension.controller.api.HardwareLightVisualState;
import com.bitwig.extension.controller.api.InternalHardwareLightState;

import java.util.Objects;

public class RgbState extends InternalHardwareLightState {

   private final int colorIndex;
   private final int altColorIndex;

   private LightState state;

   private static final RgbState[] registry = new RgbState[128];

   public RgbState(final int colorIndex, final LightState state) {
      super();
      this.colorIndex = colorIndex;
      this.state = state;
      altColorIndex = 0;
   }

   public RgbState(final int colorIndex, final LightState state, final int altColorIndex) {
      super();
      this.colorIndex = colorIndex;
      this.state = state;
      this.altColorIndex = altColorIndex;
   }

   public static RgbState of(final LaunchColor color) {
      return of(color.getIndex());
   }

   public static RgbState flash(final int colorIndex, final int altColor) {
      return new RgbState(colorIndex, LightState.FLASHING, altColor);
   }

   public static RgbState pulse(final int colorIndex) {
      return new RgbState(colorIndex, LightState.PULSING, colorIndex);
   }

   public static RgbState of(final int colorIndex) {
      final int index = Math.min(Math.max(0, colorIndex), 127);
      if (registry[index] == null) {
         registry[index] = new RgbState(index, LightState.NORMAL);
      }
      return registry[index];
   }

   @Override
   public HardwareLightVisualState getVisualState() {
      return null;
   }

   public int getColorIndex() {
      return colorIndex;
   }

   public LightState getState() {
      return state;
   }

   public void setState(final LightState state) {
      this.state = state;
   }

   @Override
   public int hashCode() {
      return Objects.hash(colorIndex, state);
   }

   @Override
   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final RgbState other = (RgbState) obj;
      return colorIndex == other.colorIndex && state == other.state;
   }


   public int getAltColor() {
      return altColorIndex;
   }
}
