package magic.card;

import magic.data.TokenCardDefinitions;
import magic.model.MagicGame;
import magic.model.MagicPermanent;
import magic.model.MagicPlayer;
import magic.model.action.MagicPlayTokenAction;
import magic.model.event.MagicEvent;
import magic.model.trigger.MagicWhenComesIntoPlayTrigger;

public class Brindle_Shoat {
    public static final MagicWhenComesIntoPlayTrigger T = new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPlayer player) {
            return new MagicEvent(
                    permanent,
                    this,
                    "PN puts a 3/3 green Boar creature token onto the battlefield.");
        }

        @Override
        public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object data[],
                final Object[] choiceResults) {
            game.doAction(new MagicPlayTokenAction(event.getPlayer(), TokenCardDefinitions.get("Boar3")));
        }
    };
}
