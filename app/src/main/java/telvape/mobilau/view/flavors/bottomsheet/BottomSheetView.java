package telvape.mobilau.view.flavors.bottomsheet;

import java.util.List;

import telvape.mobilau.model.Flavor;

/**
 *
 * Created by sbabu on 3/2/18.
 */

public interface BottomSheetView {

    void setData(List<Flavor> recipe);

    void showBottomSheet();

    void hideBottomSheet();

}
