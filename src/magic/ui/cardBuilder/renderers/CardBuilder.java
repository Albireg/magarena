package magic.ui.cardBuilder.renderers;

import java.awt.image.BufferedImage;

import magic.model.MagicAbility;
import magic.model.MagicType;
import magic.ui.cardBuilder.IRenderableCard;

public class CardBuilder {

    public static BufferedImage getCardBuilderImage(IRenderableCard cardDef) {
        //Frame type hierarchy may need adjusting
        if (cardDef.hasType(MagicType.Planeswalker)) {
            return makePlaneswalker(cardDef);
        }
        if (cardDef.hasAbility(MagicAbility.LevelUp)) {
            return makeLeveller(cardDef);
        }
        if (cardDef.isToken()) {
            return makeToken(cardDef);
        }
        if (cardDef.isFlipCard()) {
            return makeFlipCard(cardDef);
        }
        if (cardDef.isDoubleFaced()) {
            return makeDoubleFacedCard(cardDef);
        }
        if (cardDef.hasAbility(MagicAbility.Devoid)) {
            return makeDevoidCard(cardDef);
        }
        return makeBasicCard(cardDef);
    }

    private static BufferedImage makeLeveller(IRenderableCard cardDef) {
        BufferedImage cardImage = Frame.getLevellerFrameType(cardDef);
        PTFrame.drawPTPanel(cardImage, cardDef);
        ImageFrame.drawImage(cardImage, cardDef);
        OracleText.drawOracleText(cardImage, cardDef);
        TitleFrame.drawCardName(cardImage, cardDef);
        TitleFrame.drawManaCost(cardImage, cardDef);
        TypeLine.drawCardTypeLine(cardImage, cardDef);
        return cardImage;
    }

    private static BufferedImage makeDoubleFacedCard(IRenderableCard cardDef) {
        return makeBasicCard(cardDef);
    }

    private static BufferedImage makeFlipCard(IRenderableCard cardDef) {
        return makeBasicCard(cardDef);
    }

    private static BufferedImage makeToken(IRenderableCard cardDef) {
        BufferedImage cardImage = Frame.getTokenFrameType(cardDef);
        PTFrame.drawPTPanel(cardImage, cardDef);
        OracleText.drawOracleText(cardImage, cardDef);
        TitleFrame.drawCardName(cardImage, cardDef);
        TypeLine.drawCardTypeLine(cardImage, cardDef);
        return cardImage;
    }

    private static BufferedImage makePlaneswalker(IRenderableCard cardDef) {
        BufferedImage cardImage = Frame.getPlaneswalkerFrameType(cardDef);
        OracleText.drawPlaneswalkerOracleText(cardImage, cardDef);
        PTFrame.drawLoyaltyPanels(cardImage, cardDef);
        TitleFrame.drawCardName(cardImage, cardDef);
        TitleFrame.drawManaCost(cardImage, cardDef);
        TypeLine.drawCardTypeLine(cardImage, cardDef);
        return cardImage;
    }

    private static BufferedImage makeBasicCard(IRenderableCard cardDef) {
        BufferedImage cardImage = Frame.getBasicFrameType(cardDef);
        PTFrame.drawPTPanel(cardImage, cardDef);
        ImageFrame.drawImage(cardImage, cardDef);
        OracleText.drawOracleText(cardImage, cardDef);
        TitleFrame.drawCardName(cardImage, cardDef);
        TitleFrame.drawManaCost(cardImage, cardDef);
        TypeLine.drawCardTypeLine(cardImage, cardDef);
        return cardImage;
    }

    private static BufferedImage makeDevoidCard(IRenderableCard cardDef) {
        BufferedImage cardImage = Frame.getDevoidFrameType(cardDef);
        PTFrame.drawPTPanel(cardImage, cardDef);
        ImageFrame.drawImage(cardImage, cardDef);
        OracleText.drawOracleText(cardImage, cardDef);
        TitleFrame.drawCardName(cardImage, cardDef);
        TitleFrame.drawManaCost(cardImage, cardDef);
        TypeLine.drawCardTypeLine(cardImage, cardDef);
        return cardImage;
    }

}
