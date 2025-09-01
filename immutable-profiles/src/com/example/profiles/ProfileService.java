package com.example.profiles;

/**
 * Assembles immutable profiles using Builder pattern.
 */
public class ProfileService {

    public UserProfile createMinimal(String id, String email) {
        return new UserProfile.Builder(id, email).build();
    }

    public UserProfile withDisplayName(UserProfile original, String displayName) {
        return new UserProfile.Builder(original.getId(), original.getEmail())
                .phone(original.getPhone())
                .displayName(displayName)
                .address(original.getAddress())
                .marketingOptIn(original.isMarketingOptIn())
                .twitter(original.getTwitter())
                .github(original.getGithub())
                .build();
    }
}
