import {
  requireNativeComponent,
  UIManager,
  Platform,
  type ViewStyle, type NativeSyntheticEvent, type DimensionValue
} from 'react-native';

import React, { useState, useCallback } from 'react';

const LINKING_ERROR =
  `The package 'react-native-rn-klarna-osm' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

  export enum KlarnaOSMEnvironment {
    demo,
    production,
    playground,
  }
  
  export enum KlarnaOSMRegion {
    EU,
    NA,
    OC,
  }
  
  export type KlarnaOSMViewError = {
    name: string;
    message: string;
    isFatal: boolean;
  };
  
  export type KlarnaOnsiteMessagingProps = {
    style?: ViewStyle;
    clientId: string;
    placementKey: string;
    locale: string;
    environment: KlarnaOSMEnvironment;
    region: KlarnaOSMRegion;
    purchaseAmount?: number;
    onOSMViewError?: (error: KlarnaOSMViewError) => void;
  };

type InternalKlarnaOnsiteMessagingProps = Omit<
  KlarnaOnsiteMessagingProps,
  'onOSMViewError'
> & {
  onHeightChange: (event: NativeSyntheticEvent<{ height?: number }>) => void;
  onOSMViewError: (event: NativeSyntheticEvent<KlarnaOSMViewError>) => void;
};

const ComponentName = 'RnKlarnaOsmViewComponent';

export const RnKlarnaOsmView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<InternalKlarnaOnsiteMessagingProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

export const RnKlarnaOsmViewComponent: React.FC<KlarnaOnsiteMessagingProps> = ({ style, onOSMViewError, ...restProps}) => {
  const [nativeHeight, setNativeHeight] = useState<DimensionValue>(1);

  const handleheightChange = useCallback(
    (event: NativeSyntheticEvent<{height?: number}>) => {
      if(event.nativeEvent.height) {
        setNativeHeight(event.nativeEvent.height)
      }
    },
    []
  );

  const handleNativeError = useCallback(
    (event: NativeSyntheticEvent<KlarnaOSMViewError>) => {
      onOSMViewError?.(event.nativeEvent);
      setNativeHeight(0);
    },
    [onOSMViewError]
  );

  return (
    <RnKlarnaOsmView
      {...restProps}
      style={{height: nativeHeight, ...style}}
      onHeightChange={handleheightChange}
      onOSMViewError={handleNativeError}
    />
  )
}
