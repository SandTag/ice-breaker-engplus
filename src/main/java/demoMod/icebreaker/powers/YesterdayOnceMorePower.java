package demoMod.icebreaker.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import demoMod.icebreaker.IceBreaker;
import demoMod.icebreaker.cards.lightlemon.AbstractLightLemonCard;

public class YesterdayOnceMorePower extends AbstractPower {
    public static final String POWER_ID = IceBreaker.makeID("YesterdayOnceMorePower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESC;

    public YesterdayOnceMorePower(AbstractCreature owner, int amount) {
        this.owner = owner;
        this.amount = amount;
        this.ID = POWER_ID;
        this.name = NAME;
        this.updateDescription();
        this.loadRegion("time");
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESC[0], this.amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractLightLemonCard) {
            if (((AbstractLightLemonCard) card).isFetter) {
                this.flash();
                addToBot(new ApplyPowerAction(owner, owner, new TimeStasisPower(owner, this.amount)));
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESC = powerStrings.DESCRIPTIONS;
    }
}
