package me.elsiff.morefish.fishing.catcheffect

import me.elsiff.morefish.fishing.Fish
import me.elsiff.morefish.fishing.competition.FishingCompetition
import me.elsiff.morefish.resource.ResourceBundle
import me.elsiff.morefish.resource.ResourceReceiver
import org.bukkit.entity.Player

/**
 * Created by elsiff on 2018-12-25.
 */
class CatchEffectCollection(
    private val competition: FishingCompetition
) : ResourceReceiver {
    private val _effects: MutableList<CatchEffect> = mutableListOf()
    val effects: List<CatchEffect>
        get() = _effects

    override fun receiveResource(resources: ResourceBundle) {
        clear()
        addEffect(BroadcastEffect())
        addEffect(CompetitionEffect(competition))
    }

    fun addEffect(effect: CatchEffect) {
        _effects.add(effect)
    }

    fun removeEffect(effect: CatchEffect) {
        check(effect in _effects) { "Catch Effect doesn't exist" }

        _effects.remove(effect)
    }

    fun clear() {
        _effects.clear()
    }

    fun playAll(catcher: Player, fish: Fish) {
        for (effect in _effects) {
            effect.play(catcher, fish)
        }
    }
}