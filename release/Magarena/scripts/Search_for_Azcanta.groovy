def putIntoGraveyardAction = {
    final MagicGame game, final MagicEvent event ->
    if (event.isYes()) {
        game.doAction(new ShiftCardAction(event.getRefCard(), MagicLocationType.OwnersLibrary, MagicLocationType.Graveyard));
    }
}

def transformAction = {
    final MagicGame game, final MagicEvent event ->
    if (event.isYes()) {
        game.doAction(new TransformAction(event.getPermanent()));
    }
}

[
    new AtUpkeepTrigger() {
        @Override
        public boolean accept(final MagicPermanent permanent, final MagicPlayer upkeepPlayer) {
            return permanent.isController(upkeepPlayer);
        }
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent, final MagicPlayer upkeepPlayer) {
            return new MagicEvent(
                permanent,
                this,
                "PN looks at the top card of PN's library."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final MagicCard topCard = player.getLibrary().getCardAtTop();
            game.doAction(new LookAction(topCard, player, "top card of your library"));
            game.addEvent(new MagicEvent(
                event.getSource(),
                new MagicMayChoice("Put the card into your graveyard?"),
                putIntoGraveyardAction,
                "PN may\$ put it into PN's graveyard."
            ));
            if (player.getGraveyard().size() >= 7) {
                game.addEvent(new MagicEvent(
                    event.getSource(),
                    new MagicMayChoice("Transfrom ${event.getPermanent()}"),
                    transformAction,
                    "PN may\$ transform SN."
                ));
            }
        }
    }
]

