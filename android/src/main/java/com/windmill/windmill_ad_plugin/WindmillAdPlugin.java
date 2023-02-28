package com.windmill.windmill_ad_plugin;


import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodChannel;

/** WindmillAdPlugin */
public class WindmillAdPlugin implements FlutterPlugin, ActivityAware {

  // AdBannerView
  public static final String kWindmillBannerAdViewId = "flutter_windmill_ads_banner";
// AdFeedView
  public static final String kWindmillFeedAdViewId = "flutter_windmill_ads_native";

  public static final String kWindmillSplashAdViewId = "flutter_windmill_ads_splash";
// event
  public static final String kWindmillEventAdLoaded = "onAdLoaded";
  public static final String kWindmillEventAdFailedToLoad = "onAdFailedToLoad";
  public static final String kWindmillEventAdOpened = "onAdOpened";
  public static final String kWindmillEventAdClicked = "onAdClicked";
  public static final String kWindmillEventAdSkiped = "onAdSkiped";
  public static final String kWindmillEventAdReward = "onAdReward";
  public static final String kWindmillEventAdClosed = "onAdClosed";
  public static final String kWindmillEventAdVideoPlayFinished = "onAdVideoPlayFinished";
  public static final String kWindmillEventAdAutoRefreshed = "onAdAutoRefreshed";
  public static final String kWindmillEventAdRemoved = "onAdRemoved";
  public static final String kWindmillEventAdAutoRefreshFail = "onAdAutoRefreshFail";
  public static final String kWindmillEventAdRenderFail = "onAdRenderFail";
  public static final String kWindmillEventAdRenderSuccess = "onAdRenderSuccess";
  public static final String kWindmillEventAdDidDislike = "onAdDidDislike";
  public static final String kWindmillEventAdDetailViewOpened = "onAdDetailViewOpened";
  public static final String kWindmillEventAdDetailViewClosed = "onAdDetailViewClosed";

  private MethodChannel channel;
  // 插件代理
  private WindmillAdPluginDelegate delegate;

  private FlutterPluginBinding flutterPluginBinding;

  @Override
  public void onAttachedToEngine( FlutterPluginBinding flutterPluginBinding) {
    this.flutterPluginBinding = flutterPluginBinding;
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "com.windmill.ad");
  }

  @Override
  public void onDetachedFromEngine( FlutterPluginBinding binding) {
    if(channel != null){
      channel.setMethodCallHandler(null);
    }

  }

  @Override
  public void onAttachedToActivity( ActivityPluginBinding binding) {
    this.delegate = new WindmillAdPluginDelegate(this.flutterPluginBinding, binding.getActivity());
    channel.setMethodCallHandler(this.delegate);
    this.delegate.onAttachedToActivity(binding);
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    onDetachedFromActivity();
  }

  @Override
  public void onReattachedToActivityForConfigChanges( ActivityPluginBinding binding) {
    onAttachedToActivity(binding);
  }

  @Override
  public void onDetachedFromActivity() {
    this.delegate.onDetachedFromActivity();
    this.delegate = null;
  }



}
