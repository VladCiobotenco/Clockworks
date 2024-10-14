package org.firstinspires.ftc.teamcode.clockworks.teleop;

/**
 * Intuitively, a toggle-able logical trigger, most commonly used for representing a button on the
 * gamepad.
 */
public final class ToggleTrigger {

    /**
     * See {@link TriggerType}.
     */
    private final TriggerType triggerType;

    /**
     * Whatever happens when the toggle just got activated.
     */
    private final Runnable onActivate;

    /**
     * Whatever happens when the toggle just got deactivated.
     */
    private final Runnable onDeactivate;

    /**
     * The state of the <strong>physical button</strong>.
     */
    private boolean buttonState;

    /**
     * The state of the <strong>toggle</strong>.
     */
    private boolean toggleState;

    /**
     * Establishes {@link #triggerType}, the initial state of {@link #toggleState}, as well as
     * {@link #onActivate} and {@link #onDeactivate}.
     */
    public ToggleTrigger(
            TriggerType triggerType,
            boolean initialToggleState,
            Runnable onActivate,
            Runnable onDeactivate) {
        this.triggerType = triggerType;
        toggleState = initialToggleState;
        this.onActivate = onActivate;
        this.onDeactivate = onDeactivate;
    }

    /**
     * Updates the flip flop when a new state is given:
     * <ul>
     *     <li>Sets {@link #buttonState} to the given state.</li>
     *     <li>Flips {@link #toggleState}.</li>
     *     <li>Runs {@link #onActivate} or {@link #onDeactivate}, accordingly.</li>
     * </ul>
     * @param newState the new current state of the flip flop.
     */
    public void update(boolean newState) throws NullPointerException {
        if (triggerType.detect(buttonState, newState)) {
            toggleState = !toggleState;

            if (toggleState) onActivate.run();
            else onDeactivate.run();
        }
        buttonState = newState;
    }

    /**
     * Whether a falling or rising button (see {@link #buttonState}) will flip the toggle (see
     * {@link #toggleState}).
     */
    public enum TriggerType {
        RISING,
        FALLING;

        /**
         * The function which detects whether the trigger condition is met, based on the previous
         * and current state of the button.
         */
        public boolean detect(boolean previousState, boolean currentState) {
            // Needed as a workaround because for some reason returning in all cases of a switch is
            // not enough.
            boolean toReturn = false;

            switch (this) {
                case RISING:
                    toReturn = !previousState && currentState; break;
                case FALLING:
                    toReturn = previousState && !currentState; break;
            }

            return toReturn;
        }
    }
}
