#import <React/RCTViewManager.h>

@interface RCT_EXTERN_MODULE(RnKlarnaOsmViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(clientId, NSString)
RCT_EXPORT_VIEW_PROPERTY(placementKey, NSString)
RCT_EXPORT_VIEW_PROPERTY(locale, NSString)
RCT_EXPORT_VIEW_PROPERTY(environment, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(region, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(purchaseAmount, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(onHeightChange, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onOSMViewError, RCTBubblingEventBlock)

@end
