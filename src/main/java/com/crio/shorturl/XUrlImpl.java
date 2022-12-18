package com.crio.shorturl;
import java.util.*;
import java.util.Map.Entry;

public class XUrlImpl implements XUrl {
    Map<String, String> hashMap = new HashMap<>();
    Map<String, Integer> map = new HashMap<>();

    String getAlphaNumericString(int n){
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());
            
            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        
        return sb.toString();
    }

    // If longUrl already has a corresponding shortUrl, return that shortUrl
    // If longUrl is new, create a new shortUrl for the longUrl and return it
    public String registerNewUrl(String longUrl){
        if(hashMap.containsKey(longUrl)){
            map.put(hashMap.get(longUrl), map.get(hashMap.get(longUrl))+1);
            return hashMap.get(longUrl);
        }

        String endPoint = getAlphaNumericString(9);
        String shortUrl = "http://short.url/" + endPoint;
        hashMap.put(longUrl, shortUrl);
        map.put(shortUrl, 1);
        return shortUrl;
    };

    // If shortUrl is already present, return null
    // Else, register the specified shortUrl for the given longUrl
    // Note: You don't need to validate if longUrl is already present, 
    //       assume it is always new i.e. it hasn't been seen before 
    public String registerNewUrl(String longUrl, String shortUrl){
        if(hashMap.containsValue(shortUrl)){
            map.put(shortUrl, map.get(shortUrl)+1);
            return null;
        }

        hashMap.put(longUrl, shortUrl);
        map.put(shortUrl, 1);
        return shortUrl;
    };

    // If shortUrl doesn't have a corresponding longUrl, return null
    // Else, return the corresponding longUrl
    public String getUrl(String shortUrl){
        if(!hashMap.containsValue(shortUrl)){
            return null;
        }

        for(Entry<String, String> entry: hashMap.entrySet()){
            if(entry.getValue() == shortUrl) {
                return entry.getKey();
            }
        }
        return null;
    };

    // Return the number of times the longUrl has been looked up using getUrl()
    public Integer getHitCount(String longUrl){
        String shortUrl = hashMap.get(longUrl);
        if(getUrl(shortUrl) == longUrl){
            return map.get(shortUrl);
        };
        return 0;
    };

    // Delete the mapping between this longUrl and its corresponding shortUrl
    // Do not zero the Hit Count for this longUrl
    public String delete(String longUrl){
        hashMap.remove(longUrl);
        return null;
    };
}