package myfish.repository;


import myfish.domain.entities.Fish;

public interface FishRepository extends GenericRepository<Fish, String> {

    Fish update(Fish fish);
}
