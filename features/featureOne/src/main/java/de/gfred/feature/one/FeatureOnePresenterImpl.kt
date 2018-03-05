package de.gfred.feature.one

import de.gfred.shared.models.navigation.features.FeatureOneNavigator
import de.gfred.shared.models.services.StringService
import de.gfred.shared.models.tracking.TrackingService
import io.reactivex.disposables.CompositeDisposable


class FeatureOnePresenterImpl(private val navigation: FeatureOneNavigator,
                              private val tracker: TrackingService,
                              private val stringService: StringService) : FeatureOnePresenter {

    private var disposables = CompositeDisposable()

    override fun create(activity: FeatureOneActivity) {
        activity.setValue(stringService.getString())

        disposables.add(activity.onValueChanged().subscribe() {
            tracker.track("Value changed: $it")
            stringService.setString(it.toString())
        })

        disposables.add(activity.onButtonOneClicked().subscribe {
            tracker.track("Button one clicked!")
            activity.showToast(stringService.getString())
        })

        disposables.add(activity.onButtonTwoClicked().subscribe {
            tracker.track("Button two clicked!")
            navigation.showFeatureTwo()
        })
    }

    override fun destroy() = disposables.clear()
}